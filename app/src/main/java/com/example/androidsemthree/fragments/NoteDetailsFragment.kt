package com.example.androidsemthree.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidsemthree.R
import com.example.androidsemthree.database.AppDatabase
import com.example.androidsemthree.databinding.FragmentNoteDetailsBinding
import com.example.androidsemthree.models.DateToString
import com.example.androidsemthree.models.Note
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.util.*

class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {
    private var binding: FragmentNoteDetailsBinding? = null
    private var database: AppDatabase? = null
    private var client: FusedLocationProviderClient? = null
    private var calendar: Calendar? = null
    private var currentNoteId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = activity?.let { LocationServices.getFusedLocationProviderClient(it) }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding = FragmentNoteDetailsBinding.bind(view)
        database = AppDatabase.invoke(context) as AppDatabase

        binding?.apply {
            toolBar.apply {
                setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener {
                    findNavController().navigate(
                        R.id.action_noteDetailsFragment_to_noteListFragment
                    )
                }
                setOnMenuItemClickListener { onOptionsItemSelected(it) }
            }
            btnChooseDate.setOnClickListener {
                showDatePicker()
            }
        }
        checkIfNoteExists()
        setLocation()
    }

    private fun setLocation() {
        if (checkPermissions() == true) {
            getCurrentLocation()
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

    private fun checkPermissions(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED)
        }
        return null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        } else {
            returnToNoteListFragment()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            if (checkPermissions() == true) {
                client?.lastLocation?.addOnCompleteListener {
                    val location = it.result
                    if (location != null) {
                        binding?.etLongitude?.setText(location.longitude.toString())
                        binding?.etLatitude?.setText(location.latitude.toString())
                    }
                }
            }
        } else {
            startActivity(
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.icon_save -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkIfNoteExists() {
        arguments?.getInt("ARG_NOTE_ID")?.let {
            currentNoteId = it
            setNoteEditingView(it)
        }
    }

    private fun setNoteEditingView(id: Int) {
        val note = database?.noteDao()?.findById(id)
        binding?.apply {
            etTitle.setText(note?.title)
            etDesc.setText(note?.description)
            note?.date?.let {
                calendar = Calendar.getInstance()
                calendar?.time = it
                tvDate.text =
                    getString(R.string.data_text_rus) + DateToString.convertDateToString(it)
            }
        }
    }

    private fun showDatePicker() {
        calendar = Calendar.getInstance()
        val datePickerFragment = DatePickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == "REQUEST_KEY") {
                calendar?.timeInMillis = bundle.getLong("SELECTED_DATE")
                setDate(calendar)
            }
        }
        datePickerFragment.show(supportFragmentManager, "DatePickerDialog")
    }

    private fun setDate(calendar: Calendar?) {
        binding?.apply {
            tvDate.text = calendar?.time?.let {
                getString(R.string.data_text_rus) + DateToString.convertDateToString(it)
            }
        }
    }

    private fun saveNote() {
        if (currentNoteId == null && isNoteCorrect()) {
            addNote()
        } else {
            currentNoteId?.let {
                updateNote(it)
            }
        }
        returnToNoteListFragment()
    }

    private fun addNote() {
        binding?.apply {
            database?.noteDao()?.insert(
                Note(
                    null,
                    etTitle.text.toString(),
                    etDesc.text.toString(),
                    calendar?.time,
                    etLongitude.text.toString().toDouble(),
                    etLatitude.text.toString().toDouble()
                )
            )
        }
        showMessage("Задача сохранена")
        returnToNoteListFragment()
    }

    private fun updateNote(id: Int) {
        val note = database?.noteDao()?.findById(id)
        binding?.apply {
            if (isNoteCorrect()) {
                binding?.run {
                    note?.let { note ->
                        note.title = etTitle.text.toString()
                        note.description = etDesc.text.toString()
                        calendar?.also {
                            note.date = it.time
                        }
                        database?.noteDao()?.update(note)
                        showMessage("Задача обновлена")
                        returnToNoteListFragment()
                    }
                }
            }
        }
        returnToNoteListFragment()
    }

    private fun isNoteCorrect(): Boolean {
        binding?.run {
            if (etTitle.text.toString().isEmpty()) {
                showMessage("Нет названия")
                return false
            }
            if (etDesc.text.toString().isEmpty()) {
                showMessage("Нет описания")
                return false
            }
        }
        return true
    }


    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun returnToNoteListFragment() {
        findNavController().navigate(
            R.id.action_noteDetailsFragment_to_noteListFragment
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        database = null

    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
