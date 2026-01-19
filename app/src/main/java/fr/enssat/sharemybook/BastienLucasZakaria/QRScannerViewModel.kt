package fr.enssat.sharemybook.BastienLucasZakaria

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel pour le projet ShareMyBook.
 * Gère l'état du scanner pour Bastien, Lucas et Zakaria.
 */
class QRScannerViewModel : ViewModel() {
    // État interne privé
    private val _scanResult = MutableStateFlow<String?>(null)

    // État exposé à l'interface (Lecture seule)
    val scanResult: StateFlow<String?> = _scanResult.asStateFlow()

    // Fonction appelée par l'analyseur de code-barres
    fun onScanResult(result: String) {
        if (_scanResult.value != result) {
            _scanResult.value = result
        }
    }

    // Pour relancer un scan sans fermer l'écran
    fun resetScan() {
        _scanResult.value = null
    }
}