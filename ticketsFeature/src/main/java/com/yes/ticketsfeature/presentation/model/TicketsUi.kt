package com.yes.ticketsfeature.presentation.model

import com.yes.sharedmodule.presentation.model.IAdapterDelegate

data class TicketsUi(
    val tickets:List<TicketUi>
):IAdapterDelegate