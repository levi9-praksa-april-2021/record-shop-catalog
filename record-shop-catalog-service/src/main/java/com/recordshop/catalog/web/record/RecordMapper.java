package com.recordshop.catalog.web.record;

import com.recordshop.catalog.domain.record.Record;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordDTO toDto(Record record);
    List<RecordDTO> toDtoList(List<Record> records);
}
