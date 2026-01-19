package fr.enssat.sharemybook.BastienLucasZakaria.ui.camera

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import fr.enssat.sharemybook.BastienLucasZakaria.BarcodeAnalyzer
import fr.enssat.sharemybook.BastienLucasZakaria.QRScannerViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    val scannerViewModel: QRScannerViewModel = viewModel()

    // Récupération de l'activité actuelle pour renvoyer le résultat
    val activity = androidx.activity.compose.LocalActivity.current

    val scanResult by scannerViewModel.scanResult.collectAsStateWithLifecycle()

    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    // LOGIQUE DE RETOUR DE RÉSULTAT
    // Dès qu'un code est détecté, on prépare l'intent et on ferme l'activité
    LaunchedEffect(scanResult) {
        scanResult?.let { code ->
            val resultIntent = Intent().apply {
                putExtra("SCAN_RESULT", code)
            }
            // On renvoie le résultat à l'activité appelante avec RESULT_OK
            activity?.setResult(Activity.RESULT_OK, resultIntent)
            activity?.finish()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (cameraPermissionState.status.isGranted) {
                CameraPreview(viewModel = scannerViewModel)

                scanResult?.let { result ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color.Black.copy(alpha = 0.7f))
                            .padding(24.dp)
                    ) {
                        Text(text = "Livre détecté : $result", color = Color.White)
                    }
                }
            } else {
                Text(
                    text = "L'accès à la caméra est nécessaire pour Bastien, Lucas et Zakaria.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun CameraPreview(viewModel: QRScannerViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { previewView: PreviewView ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val barcodeAnalyzer = BarcodeAnalyzer { result ->
                    viewModel.onScanResult(result)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(ContextCompat.getMainExecutor(context), barcodeAnalyzer)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )
}