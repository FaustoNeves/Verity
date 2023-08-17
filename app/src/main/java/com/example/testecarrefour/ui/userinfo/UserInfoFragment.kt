package com.example.testecarrefour.ui.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageAndVideo.equals
import androidx.navigation.fragment.navArgs
import com.example.testecarrefour.R
import com.google.android.material.snackbar.Snackbar

class UserInfoFragment : Fragment() {

    private val args: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.userName.isNotBlank()) {
            Snackbar.make(requireView(), args.userName, Snackbar.LENGTH_SHORT).show()
        }
    }
}