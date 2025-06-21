package com.example.agritrek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiseaseAdapter(
    private val diseaseList: List<Disease>,
    private val onItemClick: (Disease) -> Unit
) : RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val diseaseImage: ImageView = view.findViewById(R.id.imageView)
        val diseaseName: TextView = view.findViewById(R.id.textView)
        val scientificName: TextView = view.findViewById(R.id.scientificNameTextView)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(diseaseList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_disease, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = diseaseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val disease = diseaseList[position]
        holder.diseaseImage.setImageResource(disease.imageResId)
        holder.diseaseName.text = disease.name
        holder.scientificName.text = disease.scientificName
    }
}


//class DiseaseAdapter(
//    private val diseaseList: List<Disease>,
//    private val onItemClick: (Disease) -> Unit
//) : RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val diseaseImage: ImageView = view.findViewById(R.id.imageView)
//        val diseaseName: TextView = view.findViewById(R.id.textView)
//
//        init {
//            view.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    onItemClick(diseaseList[position])
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_disease, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = diseaseList.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val disease = diseaseList[position]
//        holder.diseaseImage.setImageResource(disease.imageResId)
//        holder.diseaseName.text = disease.name
//    }
//}
