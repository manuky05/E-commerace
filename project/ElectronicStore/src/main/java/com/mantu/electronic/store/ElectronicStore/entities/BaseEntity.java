package com.mantu.electronic.store.ElectronicStore.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@Setter
@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Column(name="create_date",updatable=false)
    @CreationTimestamp
    private LocalDate careatedate;
    @Column(name="update_date",insertable = false)
    @UpdateTimestamp
    private  LocalDate updatedate;
    @Column(name="status")
    private String isActive;

}
