package com.menext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeNextTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0B0C11)
                ) {
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun MeNextTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Atmospheric Background Glows
        BackgroundGlows()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo / Emblem Placeholder (Glowing Squircle)
            GlowingEmblem()

            Spacer(modifier = Modifier.height(32.dp))

            // Typography Lockup
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Me",
                        color = Color.White,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.035).sp
                    )
                    Text(
                        text = "Next",
                        style = TextStyle(brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFF9E87), Color(0xFFD3BBFF))
                        )),
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.035).sp
                    )
                }
                Text(
                    text = "Skip the line.",
                    color = Color(0xFFA7A0B3).copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Docked Bottom Loading Experience
            LoadingBar()
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BackgroundGlows() {
    // Deep core violet aurora
    Box(
        modifier = Modifier
            .size(540.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF5B21B6).copy(alpha = 0.25f),
                        Color(0xFF6824D6).copy(alpha = 0.18f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(50)
            )
            .blur(140.dp)
    )
}

@Composable
fun GlowingEmblem() {
    Box(
        modifier = Modifier
            .size(152.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7C3AED).copy(alpha = 0.5f),
                        Color(0xFFC026D3).copy(alpha = 0.35f),
                        Color(0xFFFF5757).copy(alpha = 0.35f)
                    )
                ),
                shape = RoundedCornerShape(46.dp)
            )
            .blur(58.dp)
    )
    
    // Inner Obsidian Glass Cavity
    Box(
        modifier = Modifier
            .size(152.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF181726).copy(alpha = 0.95f),
                        Color(0xFF0E0F17).copy(alpha = 0.98f)
                    )
                )
            )
    ) {
        // Simple stylized "M" or Arrow icon representation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "▶",
                fontSize = 48.sp,
                style = TextStyle(brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFC084FC), Color(0xFFFF9E87))
                ))
            )
        }
    }
}

@Composable
fun LoadingBar() {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerTranslate"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Progress Bar Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.08f))
        ) {
            // Animated Shimmer Fill
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .offset(x = translateAnim.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xFFD3BBFF),
                                Color(0xFFFF9E87),
                                Color.Transparent
                            )
                        )
                    )
            )
        }

        // Loading Status Text
        Text(
            text = "Welcome",
            color = Color(0xFFF1EFF7).copy(alpha = 0.9f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
