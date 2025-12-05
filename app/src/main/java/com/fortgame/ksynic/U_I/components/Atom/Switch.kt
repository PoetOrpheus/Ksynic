// File: Switch.kt
// Путь: src/main/kotlin/com/fortgame/ksynic/U_I/components/Atom/Switch.kt

package com.fortgame.ksynic.U_I.components.Atom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.theme.BrandPurple
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Анимированный цвет трека
    val trackColor by animateColorAsState(
        targetValue = if (checked) Color(0xFF5D76CB) else Color(0xFF353535),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "trackColor"
    )

    Box(
        modifier = modifier
            .width(fw(46))
            .height(fh(24))
            .border(2.dp,trackColor, RoundedCornerShape(fh(30)))
            .clip(CircleShape)
            .background(Color.White)
            .indication(
                interactionSource = interactionSource,
                indication = ripple(
                    bounded = true,
                    radius = 24.dp,
                    color = BrandPurple.copy(alpha = 0.3f)
                )
            )
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(3.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .width(fw(20))
                .height(fh(20))
                .offset(x = if (checked) fw(20) else 0.dp)
                .border(2.dp,Color.White,CircleShape)
                .clip(CircleShape)
                .background(trackColor)
        )
    }
}

// =============================================================================
// Preview
// =============================================================================

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun SwitchPreview() {
    var checked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.Text("Выкл", color = Color.Gray)
            Spacer(Modifier.width(16.dp))
            Switch(checked = checked, onCheckedChange = { checked = it })
            Spacer(Modifier.width(16.dp))
            androidx.compose.material3.Text("Вкл", color = BrandPurple)
        }

        // Тест разных состояний
        Switch(checked = false, onCheckedChange = {})
        Switch(checked = true, onCheckedChange = {})
    }
}