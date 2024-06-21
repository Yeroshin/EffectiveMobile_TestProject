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
           val ar=mapStringToDate(it.arrival.date)
            val dep=mapStringToDate(it.departure.date)
            val dif=ar.time-dep.time
            val hoursDifference = dif.toDouble() / (1000 * 60 * 60)
           // val hoursDifference = TimeUnit.MILLISECONDS.toHours(dif)
            val formattedString = "%.1f ч".format(hoursDifference.toDouble()).replace(".0", "")
            if(it.badge=="null"){
                val t:String=it.badge
            }else{
                val t:String=it.badge
            }
            if(it.badge==null){
                val t:String=it.badge
            }else{
                val t:String=it.badge
            }
            TicketUi(
                it.badge,
                formatNumberWithSpaces(it.price.value),
                mapDateToString(mapStringToDate(it.departure.date)),
                mapDateToString(mapStringToDate(it.arrival.date)),
                it.departure.airport,
                it.arrival.airport,
                it.has_transfer
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