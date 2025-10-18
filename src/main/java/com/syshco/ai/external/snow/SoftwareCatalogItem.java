package com.syshco.ai.external.snow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareCatalogItem {
    private String id;
    private String name;
    private String description;
    private float[] embedding;

    public SoftwareCatalogItem(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
