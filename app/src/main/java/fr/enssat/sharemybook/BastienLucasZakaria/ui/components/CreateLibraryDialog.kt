package fr.enssat.sharemybook.BastienLucasZakaria.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CreateLibraryDialog(
    onCreate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(), // La Card prend toute la largeur disponible
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                // Ajout d'un padding intérieur pour espacer le contenu des bords de la Card
                modifier = Modifier.padding(16.dp),
                // Espacement vertical entre les éléments de la colonne
                verticalArrangement = Arrangement.spacedBy(16.dp),
                // Centre les éléments horizontalement
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Créer une bibliothèque",
                    style = MaterialTheme.typography.titleLarge // titleLarge est plus approprié pour un titre de dialogue
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nom de la bibliothèque") },
                    // Le champ de texte prend toute la largeur pour une meilleure UX
                    modifier = Modifier.fillMaxWidth(),
                    // Le champ de texte ne doit avoir qu'une seule ligne
                    singleLine = true
                )

                // Utilisation d'une Row pour aligner les boutons sur la même ligne
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End, // Aligne les boutons à droite
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Annuler")
                    }

                    // Ajout d'un espace entre les deux boutons
                    Button(
                        onClick = {
                            // La vérification isNotBlank est importante pour éviter les noms vides
                            if (name.isNotBlank()) {
                                onCreate(name)
                            }
                        },
                        // Désactive le bouton si le nom est vide
                        enabled = name.isNotBlank()
                    ) {
                        Text("Créer")
                    }
                }
            }
        }
    }
}
