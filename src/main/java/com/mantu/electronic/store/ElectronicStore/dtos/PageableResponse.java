package com.mantu.electronic.store.ElectronicStore.dtos;

import lombok.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class PageableResponse<T>  {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;


}