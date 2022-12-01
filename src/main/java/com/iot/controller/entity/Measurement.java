package com.iot.controller.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(indexes = {
        @Index(name = "asset_id_idx", columnList = "asset_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    @Id
    @SequenceGenerator(
            name = "measurement_gen",
            sequenceName = "measurement_seq",
            initialValue = 1, allocationSize = 100)
    @GeneratedValue(
            generator = "measurement_seq",
            strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "asset_id", updatable = false, nullable = false)
    private Long assetId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date timestamp;

    private Float value;

}
