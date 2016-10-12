create index IX_2F5D2429 on OSB_SCV_MappingDataSource (companyId);

create index IX_7002601D on OSB_SCV_UserMappingRule (companyId, destinationField[$COLUMN_LENGTH:75$]);
create index IX_313771B9 on OSB_SCV_UserMappingRule (mappingDataSourceId, frequency);