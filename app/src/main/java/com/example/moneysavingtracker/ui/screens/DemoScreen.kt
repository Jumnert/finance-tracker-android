package com.example.moneysavingtracker.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneysavingtracker.R
import com.example.moneysavingtracker.ui.screens.components.TransactionButtons
import com.example.moneysavingtracker.ui.theme.CardGradient1
import com.example.moneysavingtracker.ui.theme.MoneySavingTrackerTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen() {
    Scaffold(
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = { onAddIncome() },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Income")
                }

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                        text = "Jumnert",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box (
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        
                        painter = painterResource(id = R.drawable.pfp),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize().border(4.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            brush = CardGradient1,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Balance",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$12,450.00",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "+$1,200 this month",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            // Right after your Total Balance Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                   ,
                horizontalArrangement = Arrangement.spacedBy(16.dp) // spacing between buttons
            ) {
                TransactionButtons()
            }


            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Activity",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium


                    )
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }



          Row(modifier = Modifier.fillMaxWidth(),

              verticalAlignment = Alignment.CenterVertically) {
              val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
              val currentDate = formatter.format(Date())

              Card(
                  modifier = Modifier.fillMaxWidth(),
                  shape = MaterialTheme.shapes.medium
              ) {
                  Column(
                      modifier = Modifier
                          .fillMaxWidth()
                          .shadow(1.dp, shape = MaterialTheme.shapes.medium, clip = true)
                          .background(Color.White)
                          .padding(10.dp)
                  ) {
                      // One transaction row
                      Row(
                          modifier = Modifier.fillMaxWidth().padding(9.dp, 10.dp),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text(
                              text = currentDate,
                              style = MaterialTheme.typography.bodyLarge
                          )
                          Text(
                              text = "+100$",
                              color = MaterialTheme.colorScheme.primary,
                              style = MaterialTheme.typography.bodyLarge
                          )
                      }
                      // Divider after the row
                      HorizontalDivider(
                          thickness = 2.dp,
                          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                          modifier = Modifier.padding(top = 8.dp)
                      )
                      Row(
                          modifier = Modifier.fillMaxWidth().padding(1.dp, 10.dp),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text(
                              text = currentDate,
                              style = MaterialTheme.typography.bodyLarge
                          )
                          Text(
                              text = "+100$",
                              color = MaterialTheme.colorScheme.primary,
                              style = MaterialTheme.typography.bodyLarge
                          )
                      }
                      // Divider after the row
                      HorizontalDivider(
                          thickness = 2.dp,
                          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                          modifier = Modifier.padding(top = 8.dp)
                      )
                      Row(
                          modifier = Modifier.fillMaxWidth().padding(1.dp, 10.dp),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text(
                              text = currentDate,
                              style = MaterialTheme.typography.bodyLarge
                          )
                          Text(
                              text = "+100$",
                              color = MaterialTheme.colorScheme.primary,
                              style = MaterialTheme.typography.bodyLarge
                          )
                      }
                      // Divider after the row
                      HorizontalDivider(
                          thickness = 2.dp,
                          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                          modifier = Modifier.padding(top = 8.dp)
                      )
                      Row(
                          modifier = Modifier.fillMaxWidth().padding(1.dp, 10.dp),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text(
                              text = currentDate,
                              style = MaterialTheme.typography.bodyLarge
                          )
                          Text(
                              text = "+100$",
                              color = MaterialTheme.colorScheme.primary,
                              style = MaterialTheme.typography.bodyLarge
                          )
                      }
                      // Divider after the row
                      HorizontalDivider(
                          thickness = 2.dp,
                          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                          modifier = Modifier.padding(top = 8.dp)
                      )


                      // Repeat more rows here in a loop for each transaction
                  }
              }


          }
        }
    }
}

private fun RowScope.onAddExpense() {
    TODO("Not yet implemented")
}

private fun RowScope.onAddIncome() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DemoScreenPreview() {
    MoneySavingTrackerTheme {
        DemoScreen()
    }
}
