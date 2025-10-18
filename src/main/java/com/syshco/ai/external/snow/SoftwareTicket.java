package com.syshco.ai.external.snow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareTicket {
    private String ticketNumber;
    private String softwareName;
    private String requestedBy;
    private String status;
}
