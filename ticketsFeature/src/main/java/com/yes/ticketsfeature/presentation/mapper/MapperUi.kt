package com.yes.ticketsfeature.presentation.mapper

import com.yes.ticketsfeature.domain.model.Tickets
import com.yes.ticketsfeature.presentation.model.TicketUi
import com.yes.ticketsfeature.presentation.model.TicketsUi
import java.text.SimpleDateFormat
import java.util.Date

class MapperUi {
    fun map(tickets: Tickets?):List<TicketUi>?{

        return tickets?.tickets?.map {
            val t=mapDateToString(mapStringToDate(it.departure.date))
            val x=t
            TicketUi(
                it.badge,
                formatNumberWithSpaces(it.price.value),
                mapDateToString(mapStringToDate(it.departure.date)),
                mapDateToString(mapStringToDate(it.arrival.date))
            )
        }
    }
    private fun formatNumberWithSpaces(number: Int): String {
        val formattedNumber = String.format("%,d", number).replace(",", " ")
        return "от $formattedNumber \u20BD"
    }
    private fun mapDateToString(date: Date): String {
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    private fun mapStringToDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return format.parse(date)
    }
}