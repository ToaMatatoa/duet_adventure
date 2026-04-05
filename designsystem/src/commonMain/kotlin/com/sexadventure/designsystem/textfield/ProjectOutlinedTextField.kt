package com.sexadventure.designsystem.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp

@Composable
fun ProjectOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    valueMaxLength: Int = Int.MAX_VALUE,
    label: String = "",
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newDescription ->
            if (newDescription.length <= valueMaxLength) {
                onValueChange(newDescription)
            }
        },
        shape = RoundedCornerShape(size = 12.dp),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
        },
        supportingText = {
            if (value.isNotEmpty()) {
                Text(
                    text = "${value.length} / $valueMaxLength",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    textAlign = End,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        singleLine = singleLine,
        isError = isError,
        enabled = enabled,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier,
    )
}
