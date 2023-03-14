package com.knoldus.electricity

case class ElectricityAccount(accountNumber: Int, username: String, Address: String, previous_unit: Int, current_unit: Int) {

  private val units_consumed = current_unit - previous_unit

  // Function to calculate the Electricity Charges using Price unit slab
  def calculateAmount(): Double = {

    if (units_consumed < 0) {
      throw new IllegalArgumentException()
    }
    else if (units_consumed <= 250) {
      val amount = units_consumed * 5.25
      amount
    }
    else if (units_consumed <= 450) {
      val amount = (250 * 5.25) + (units_consumed - 250) * 6.75
      amount
    }
    else {
      val amount = (250 * 5.25) + (200 * 6.75) + (units_consumed - 450) * 8.50
      amount
    }
  }
}
