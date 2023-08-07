package com.saad.fragmentsmvvm

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.saad.fragmentsmvvm.api.ApiInterface
import com.saad.fragmentsmvvm.api.RetroObject
import com.saad.fragmentsmvvm.model.Meme
import com.saad.fragmentsmvvm.repository.MemesRepository
import com.saad.fragmentsmvvm.viewmodel.MemesViewModel
import com.saad.fragmentsmvvm.viewmodel.ViewModelFactory

class MemesDisplay : Fragment() {
    var i = 0
    lateinit var img: ImageView
    lateinit var memeText : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_memes_display, container, false)
        val next = view.findViewById<Button>(R.id.next)
        val prev = view.findViewById<Button>(R.id.previous)
        img = view.findViewById(R.id.imageView)
        memeText = view.findViewById(R.id.memeText)
        val service = RetroObject.getInstance().create(ApiInterface::class.java)
        val repo = MemesRepository(service)
        val viewModel = ViewModelProvider(this, ViewModelFactory(repo))[MemesViewModel::class.java]
        viewModel.memes.observe(viewLifecycleOwner){jokes ->
            setMeme(meme = jokes.data.memes[i])
            next.setOnClickListener {
                val meme = jokes.data.memes[++i]
                setMeme(meme)
            }
            prev.setOnClickListener {
                val meme = jokes.data.memes[--i]
                setMeme(meme)
            }
        }
        return view
    }

    private fun setMeme(meme: Meme) {
        memeText.text = meme.name
        Glide.with(requireContext())
            .load(meme.url)
            .into(img)
    }

}