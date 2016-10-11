create index IX_2F5D2429 on OSB_SCV_MappingDataSource (companyId);

create index IX_C04071DD on OSB_SCV_UserMappingRule (companyId);
create index IX_313771B9 on OSB_SCV_UserMappingRule (mappingDataSourceId, frequency);