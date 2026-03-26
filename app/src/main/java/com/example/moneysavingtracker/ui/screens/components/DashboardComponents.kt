package com.example.moneysavingtracker.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.moneysavingtracker.R

@Composable
fun DashboardHeader(
    displayName: String,
    photoUrl: Any? = null,
    onLogout: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = displayName,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (onLogout != null) {
            var expanded by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { expanded = true },
                contentAlignment = Alignment.Center
            ) {
                ProfileAvatar(photoUrl = photoUrl)

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            expanded = false
                            onLogout()
                        },
                        leadingIcon = {
                            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                        }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                ProfileAvatar(photoUrl = photoUrl)
            }
        }
    }
}

@Composable
fun ProfileAvatar(photoUrl: Any? = null) {
    if (photoUrl != null) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "Profile",
            modifier = Modifier
                .fillMaxSize()
                .border(4.dp, Color.White, CircleShape),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.pfp),
            fallback = painterResource(id = R.drawable.pfp),
            placeholder = painterResource(id = R.drawable.pfp)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.pfp),
            contentDescription = "Profile",
            modifier = Modifier
                .fillMaxSize()
                .border(4.dp, Color.White, CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BalanceSummaryCard(
    balanceText: String,
    footerText: String,
    modifier: Modifier = Modifier,
    brush: Brush,
    onRefreshClick: (() -> Unit)? = null,
    onVisibilityClick: (() -> Unit)? = null,
    visibilityIcon: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(
                    brush = brush,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Balance",
                        color = Color.White.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Row {
                        if (onRefreshClick != null) {
                            IconButton(onClick = onRefreshClick) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Switch Currency",
                                    tint = Color.White
                                )
                            }
                        }
                        if (onVisibilityClick != null && visibilityIcon != null) {
                            IconButton(onClick = onVisibilityClick) {
                                visibilityIcon()
                            }
                        }
                    }
                }

                Text(
                    text = balanceText,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = footerText,
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    actionText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = actionText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
