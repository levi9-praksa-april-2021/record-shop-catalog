package com.recordshop.catalog.web.record;

import java.util.List;

import org.mapstruct.Mapper;

import com.recordshop.catalog.domain.record.Record;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordDTO toDto(Record record);
    List<RecordDTO> toDtoList(List<Record> records);
}
