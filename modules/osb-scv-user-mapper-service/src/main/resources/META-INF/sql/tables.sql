create table OSB_SCV_UserMappingRule (
	userMappingRuleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dataSourceId LONG,
	fieldSetId LONG,
	modelName VARCHAR(75) null,
	sourceField VARCHAR(75) null,
	destinationField VARCHAR(75) null,
	frequency INTEGER
);