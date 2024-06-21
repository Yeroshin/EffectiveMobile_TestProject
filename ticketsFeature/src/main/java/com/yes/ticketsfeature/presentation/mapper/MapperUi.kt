package com.yes.ticketsfeature.presentation.mapper

import com.yes.ticketsfeature.domain.model.Tickets
import com.yes.ticketsfeature.presentation.model.TicketUi
import com.yes.ticketsfeature.presentation.model.TicketsUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class MapperUi {
    fun map(tickets: Tickets?):List<TicketUi>?{

        return tickets?.tickets?.map {
            TicketUi(
                it.badge,
                formatNumberWithSpaces(it.price.value),
                mapDateToString(mapStringToDate(it.departure.date)),
                mapDateToString(mapStringToDate(it.arrival.date)),
                it.departure.airport,
                it.arrival.airport,
                if (it.has_transfer){"/без пересадок"
                }else{""},
                timeDeference(it.arrival.date,it.departure.date)
            )
        }
    }
    private fun timeDeference(arrival:String,departure:String):String{
        val ar=mapStringToDate(arrival)
        val dep=mapStringToDate(departure)
        val dif=ar.time-dep.time
        val hoursDifference = dif.toDouble() / (1000 * 60 * 60)
        return "%.1fч".format(hoursDifference).replace(".0", "")+" в пути"

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