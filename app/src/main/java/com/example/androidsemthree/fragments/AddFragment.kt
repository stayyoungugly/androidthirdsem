package com.example.androidsemthree.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidsemthree.R
import com.example.androidsemthree.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {

    private var binding: FragmentAddBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
