Electricity-Charges-Assignment

***MAIN FILE***

```
package com.knoldus.electricity

case class ElectricityAccount(accountNumber: Int, username: String, Address: String, previous_unit: Int, current_unit: Int) {

  private val units_consumed = current_unit - previous_unit

  // Function to calculate the Electricity Charges using Price unit slab
  def calculateAmount(): Double = {

    if (units_consumed < 0) {
      throw new IllegalArgumentException()
    }
    else if (units_consumed <= 250) {
      val amount = (units_consumed * 5.25)
      val gst_amount = amount + (amount * 18/100)
      gst_amount
    }
    else if (units_consumed <= 450) {
      val amount = (250 * 5.25) + (units_consumed - 250) * 6.75
      val gst_amount = amount + (amount * 18/100)
      gst_amount
    }
    else {
      val amount = (250 * 5.25) + (200 * 6.75) + (units_consumed - 450) * 8.50
      val gst_amount = amount + (amount * 18/100)
      gst_amount
    }
  }
}

```

***Test File***

```
package com.knoldus.electricity

import org.scalatest.funsuite.AnyFunSuite

class ElectricityAccountTest extends AnyFunSuite {

  test("calculateAmount() should return correct amount for 0 units consumed") {
    val account = ElectricityAccount(101, "Tushar", "Noida", 0, 0)
    assert(account.calculateAmount() == 0.0)
  }

  test("calculateAmount() should return correct amount for units consumed between 0 and 250") {
    val account = ElectricityAccount(101, "Tushar", "Noida", 100, 200)
    assert(account.calculateAmount() == 619.5)
  }

  test("calculateAmount() should return correct amount for units consumed between 251 and 450") {
    val account = ElectricityAccount(101, "Tushar", "Noida", 200, 500)
    assert(account.calculateAmount() == 1947.0)
  }

  test("calculateAmount() should return correct amount for units consumed above 450 ") {
    val account = ElectricityAccount(101, "Tushar", "Noida", 5062, 5904)
    assert(account.calculateAmount() == 7073.51)
  }

  // Test case for handling the Exception.
  test("throw IllegalArgumentException when units consumed is negative") {
    val account = ElectricityAccount(101, "Tushar", "Noida", 500, 150)
    assertThrows[IllegalArgumentException] {
      account.calculateAmount()
    }
  }
}

```
