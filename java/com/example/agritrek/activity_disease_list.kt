package com.example.agritrek

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_disease_list : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var diseaseList: List<Disease>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_list)

        // Set up Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Common Diseases"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Back arrow functionality
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerView)
        diseaseList = listOf(
            Disease(
                "Leaf Spot",
                "Cercospora spp.",
                R.drawable.img_5,
                "Causes circular brown spots on leaves, often with a yellow halo. Severe infections lead to premature leaf drop, reducing photosynthesis. It thrives in warm, moist conditions and spreads through water splashes.",
                "Remove and destroy infected leaves. Avoid overhead watering and ensure good air circulation. Apply a broad-spectrum fungicide to prevent further spread."
            ),
            Disease(
                "Powdery Mildew",
                "Erysiphe spp.",
                R.drawable.img_6,
                "White or grayish powder appears on the surface of leaves, stems, and flowers. Infected plants show stunted growth and distorted leaves. It favors dry, warm days and cool nights.",
                "Apply sulfur-based or potassium bicarbonate sprays. Improve air circulation by proper pruning. Avoid excess nitrogen fertilizers, which promote susceptible new growth."
            ),
            Disease(
                "Root Rot",
                "Pythium spp.",
                R.drawable.img_7,
                "Roots become brown, soft, and mushy, leading to plant wilting and yellowing. Caused mainly by poor drainage and overwatering. It can quickly spread in moist soil conditions.",
                "Improve drainage and allow soil to dry between waterings. Remove severely affected plants. Treat soil with fungicides containing mefenoxam or phosphonate."
            ),
            Disease(
                "Downy Mildew",
                "Plasmopara spp.",
                R.drawable.img_8,
                "Yellow or pale green spots appear on the upper leaf surface, with white or grayish fuzz underneath. It thrives in cool, moist environments. Severe cases can defoliate the plant.",
                "Use resistant varieties and ensure proper plant spacing. Water early in the day to allow drying. Apply fungicides like copper hydroxide or mancozeb preventively."
            ),
            Disease(
                "Anthracnose",
                "Colletotrichum spp.",
                R.drawable.img_9,
                "Causes dark, sunken lesions on fruits, leaves, and stems. In wet conditions, lesions may ooze pink spores. It weakens plant tissues, making them prone to further infections.",
                "Prune and destroy infected parts immediately. Maintain plant health with balanced nutrition. Apply preventive fungicides during wet seasons."
            ),
            Disease(
                "Blight",
                "Phytophthora infestans",
                R.drawable.img_10,
                "Sudden browning and collapse of leaves, stems, or flowers. Often starts at the tips and moves downward. Highly destructive and can decimate crops quickly in moist weather.",
                "Use resistant plant varieties and maintain proper spacing. Improve drainage and avoid overhead irrigation. Apply fungicides like chlorothalonil or copper compounds."
            ),
            Disease(
                "Rust",
                "Puccinia spp.",
                R.drawable.img_11,
                "Small, orange or rust-colored pustules appear on leaves and stems. It spreads via wind and water, often weakening the plant and reducing yield.",
                "Remove and destroy infected leaves. Use rust-resistant plant varieties. Apply sulfur-based or systemic fungicides early in the infection cycle."
            ),
            Disease(
                "Wilt",
                "Fusarium spp.",
                R.drawable.img_12,
                "Plant wilts despite adequate watering. Leaves may yellow and drop prematurely. The fungus blocks water flow in the plant’s vascular system, leading to permanent damage.",
                "Improve soil drainage and avoid planting in infected soil. Use disease-resistant varieties and rotate crops regularly. Soil solarization may reduce fungal presence."
            ),
            Disease(
                "Canker",
                "Botryosphaeria spp.",
                R.drawable.img_13,
                "Causes sunken, dead patches on stems or branches, often with cracked bark. Can girdle branches, leading to dieback. Usually enters through wounds.",
                "Prune and destroy affected branches during dry weather. Disinfect pruning tools between cuts. Apply fungicide to protect wounds and prevent spread."
            ),
            Disease(
                "Mosaic Virus",
                "Tobacco mosaic virus (TMV)",
                R.drawable.img_14,
                "Mottled light and dark green or yellow patterns appear on leaves. Growth is stunted, and fruit production is reduced. The virus spreads through contaminated tools or insects.",
                "Remove and destroy infected plants immediately. Control insect vectors like aphids. Disinfect tools and avoid smoking near plants, as TMV can spread via tobacco products."
            ),
            Disease(
                "Sooty Mold",
                "Capnodium spp.",
                R.drawable.img_15,
                "Black, soot-like fungal growth appears on leaves and stems. It grows on honeydew excreted by sap-sucking insects. While not directly harmful, it blocks sunlight and hinders photosynthesis.",
                "Control insects like aphids, whiteflies, and mealybugs. Wash off mold with a mild soap solution. Maintain plant health with proper fertilization and watering."
            ),
            Disease(
                "Scab",
                "Venturia inaequalis",
                R.drawable.img_16,
                "Causes rough, scabby lesions on fruits, leaves, and twigs. Affects both the aesthetic and market value of fruits. It develops in wet, humid conditions during the growing season.",
                "Prune to improve air circulation. Rake and dispose of fallen leaves. Apply fungicides like captan or mancozeb during early bud and fruit stages."
            )

        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DiseaseAdapter(diseaseList) { selectedDisease ->
            val intent = Intent(this, DiseaseDetailActivity::class.java)
            intent.putExtra("disease", selectedDisease)
            startActivity(intent)
        }
    }
}
