package fr.enssat.sharemybook.BastienLucasZakaria

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

// On définit la classe qui implémente l'interface Analyzer de CameraX
class BarcodeAnalyzer(private val onBarcodeDetected: (String) -> Unit) : ImageAnalysis.Analyzer {

    // 1. Configuration du scanner (IA ML Kit)
    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    // 2. La fonction obligatoire à remplacer (override) pour analyser les images
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image

        if (mediaImage != null) {
            // Préparation de l'image pour l'IA
            val image = InputImage.fromMediaImage(
                mediaImage,
                imageProxy.imageInfo.rotationDegrees
            )

            // 3. Lancement de la reconnaissance
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    // On parcourt les codes trouvés (boucle for)
                    for (barcode in barcodes) {
                        barcode.rawValue?.let { value ->
                            // On envoie le résultat (ISBN) vers le ViewModel
                            onBarcodeDetected(value)
                        }
                    }
                }
                .addOnFailureListener {
                    // Erreur d'analyse (optionnel)
                }
                .addOnCompleteListener {
                    // 4. IMPORTANT : On ferme l'image pour libérer la caméra
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}