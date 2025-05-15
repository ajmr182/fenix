package com.eve.fenix.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.fenix.databinding.FragmentTaskBinding
import com.google.firebase.firestore.FirebaseFirestore

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val db = FirebaseFirestore.getInstance()

        db.collection("task2")
            .get()
            .addOnSuccessListener { result ->
                val listToDoTask = mutableListOf<ToDoTask>()
                for (document in result) {
                    val task = document.toObject(ToDoTask::class.java)
                    listToDoTask.add(task)
                }

                val customAdapter = CustomAdapter(listToDoTask)
                binding.recyclerTask.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerTask.adapter = customAdapter
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE", "Error al obtener documentos", exception)
            }
    }
}