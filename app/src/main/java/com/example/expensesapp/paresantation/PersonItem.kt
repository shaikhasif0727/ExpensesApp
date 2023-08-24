package com.example.expensesapp.paresantation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class DropDownItem(
    val text:String
)

@Composable
fun PersonItem(
    personName: String,
    dropDownItem: List<DropDownItem>,
    modifier: Modifier = Modifier,
    onItemClick: (DropDownItem) -> Unit
) {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RectangleShape,
        modifier = modifier.
        onSizeChanged {
            itemHeight = with(density) {it.height.toDp()}
        },
    ) {

        Box(
           modifier = Modifier
               .fillMaxWidth()
               .indication(interactionSource, LocalIndication.current)
               .pointerInput(true) {
                   detectTapGestures(
                       onLongPress = {
                           isContextMenuVisible = true
                           pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                       },
                       onPress = {
                           val press = PressInteraction.Press(it)
                           interactionSource.emit(press)
                           tryAwaitRelease()
                           interactionSource.emit(PressInteraction.Release(press))
                       }
                   )
               }
               .padding(16.dp)
        ) {
            Text(text = personName)
        }
        
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropDownItem.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.text) },
                    onClick = {
                        onItemClick(item)
                        isContextMenuVisible = false
                    }
                )
            }
        }

    }

}