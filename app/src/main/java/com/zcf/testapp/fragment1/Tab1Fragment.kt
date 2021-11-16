package com.zcf.testapp.fragment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.zcf.testapp.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment() {

    private lateinit var binding: FragmentTab1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeContent.setContent {
            MdcTheme() {
                ComposableContent()
            }
        }
    }

    @Composable
    fun ComposableContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Composable Text")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ModeImage(imageVector = Icons.Default.AccountCircle, contentDescription = "image 1")
                ModeImage(imageVector = Icons.Default.AddCircle, contentDescription = "image 2")
            }

            var volume by remember { mutableStateOf(40f) }

            VolumeSlider(
                value = volume,
                onValueChange = { volume = it },
                valueRange = 0f..100f,
                steps = 100
            )

            StatefulButton()

            Button(onClick = {}) {
                Text("Just a normal button")
            }

            ToggleButtonGroup(enabled = false, initialSelection = 1) {
                println("zcff selected: $it")
            }

            ToggleButtonGroup2(enabled = false, initialSelection = 1) {
                println("zcff selected: $it")
            }

        }
    }

    @Composable
    fun ModeImage(
        imageVector: ImageVector,
        contentDescription: String?
    ) = Image(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "image 1",
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
    )

    @Composable
    fun VolumeSlider(
        value: Float,
        onValueChange: (Float) -> Unit,
        valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
        steps: Int = 0,
    ) {

        return Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent
            )
        )
    }

    @Composable
    fun StatefulButton() {
        Row {
            var isEnabled by remember { mutableStateOf(true) }

            Button(
                onClick = { },
                modifier = Modifier.defaultMinSize(minWidth = 160.dp),
                enabled = isEnabled
            ) {

                Text("I am ${if (isEnabled) "enabled" else "disabled"}")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    isEnabled = !isEnabled
                },
                modifier = Modifier.defaultMinSize(minWidth = 100.dp)
            ) {
                Text(text = if (isEnabled) "Disable" else "Enable")
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ToggleButtonGroup(
        enabled: Boolean,
        initialSelection: Int,
        onSelectionChanged: (Int) -> Unit
    ) {
        val colors = ButtonDefaults.buttonColors()
        val contentColor by colors.contentColor(enabled)

        var selectedOption by remember { mutableStateOf(initialSelection) }


        Surface(
            shape = MaterialTheme.shapes.small,
            color = colors.backgroundColor(enabled = enabled).value,
            contentColor = contentColor.copy(alpha = 1f),

            ) {
            CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
                ProvideTextStyle(value = MaterialTheme.typography.button) {
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Max),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Option 1",
                            modifier = Modifier
                                .background(colors.backgroundColor(enabled = selectedOption == 0).value)
                                .clickable {
                                    selectedOption = 0
                                    onSelectionChanged(0)
                                }
                                .padding(8.dp),
                            color = colors.contentColor(enabled = selectedOption == 0).value
                        )

//                        Box(modifier = Modifier.padding(8.dp).width(1.dp).height(20.dp).background(colors.contentColor(enabled = true).value))
//                        Divider(modifier = Modifier.fillMaxHeight().width(Dp.Hairline), color = colors.contentColor(false).value)
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .padding(vertical = 8.dp)
                                .fillMaxHeight()
                                .background(colors.contentColor(enabled = false).value.copy(alpha = 0.2f))
                        )

                        Text(
                            text = "Option 1",
                            modifier = Modifier
                                .background(colors.backgroundColor(enabled = selectedOption == 1).value)
                                .clickable {
                                    selectedOption = 1
                                    onSelectionChanged(1)
                                }
                                .padding(8.dp),
                            color = colors.contentColor(enabled = selectedOption == 1).value
                        )

//                        Box(modifier = Modifier.padding(8.dp).width(1.dp).height(20.dp).background(colors.contentColor(enabled = true).value))
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .padding(vertical = 8.dp)
                                .fillMaxHeight()
                                .background(colors.contentColor(enabled = false).value.copy(alpha = 0.2f))
                        )

                        Text(
                            text = "Option 1",
                            modifier = Modifier
                                .background(colors.backgroundColor(enabled = selectedOption == 2).value)
                                .clickable {
                                    selectedOption = 2
                                    onSelectionChanged(2)
                                }
                                .padding(8.dp),
                            color = colors.contentColor(enabled = selectedOption == 2).value
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ToggleButtonGroup2(
        enabled: Boolean,
        initialSelection: Int,
        onSelectionChanged: (Int) -> Unit
    ) {
        val colors = ButtonDefaults.buttonColors()
        val contentColor by colors.contentColor(enabled)

        var selectedOption by remember { mutableStateOf(initialSelection) }

        Row(
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            Button(
                onClick = {
                },
                modifier = Modifier.clickable {
                    selectedOption = 0
                    onSelectionChanged(0)
                },
                enabled = selectedOption == 0,
                shape = MaterialTheme.shapes.small.copy(
                    topEnd = CornerSize(0),
                    bottomEnd = CornerSize(0)
                )
            ) {
                Text("Option 1")
            }

            Button(
                onClick = {
                },
                modifier = Modifier.clickable {
                    selectedOption = 1
                    onSelectionChanged(1)
                },
                enabled = selectedOption == 1,
                shape = RectangleShape
            ) {
                Text("Option 2")
            }


            Button(
                onClick = {
                },
                modifier = Modifier.clickable {
                    selectedOption = 2
                    onSelectionChanged(2)
                },
                enabled = selectedOption == 2,
                shape = MaterialTheme.shapes.small.copy(
                    topStart = CornerSize(0),
                    bottomStart = CornerSize(0)
                )
            ) {
                Text("Option 3")
            }
        }
    }


}