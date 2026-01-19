package fr.enssat.sharemybook.BastienLucasZakaria

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel pour le scanner de codes-barres (100% Kotlin).
 * Gère l'état du scan pour Bastien, Lucas et Zakaria.
 */
class QRScannerViewModel : ViewModel() {
    // État interne (Private)
    private val _scanResult = MutableStateFlow<String?>(null)

    // État exposé à la vue (Public & Read-only)
    val scanResult: StateFlow<String?> = _scanResult.asStateFlow()

    /**
     * Reçoit le résultat de l'analyseur (ML Kit).
     * @param result La valeur brute du code-barres (ISBN).
     */
    fun onScanResult(result: String) {
        // Évite les mises à jour inutiles si le code est identique
        if (_scanResult.value != result) {
            _scanResult.value = result
        }
    }

    /**
     * Réinitialise le résultat pour permettre un nouveau scan.
     */
    fun resetScan() {
        _scanResult.value = null
    }
}