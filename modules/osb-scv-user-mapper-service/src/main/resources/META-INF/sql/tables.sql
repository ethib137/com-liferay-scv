create table OSB_SCV_MappingDataSource (
	mappingDataSourceId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url VARCHAR(75) null,
	login VARCHAR(75) null,
	password_ VARCHAR(75) null,
	type_ LONG,
	availableFields TEXT null
);

create table OSB_SCV_UserMappingRule (
	userMappingRuleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	mappingDataSourceId LONG,
	fieldSetId LONG,
	modelName VARCHAR(75) null,
	sourceField VARCHAR(75) null,
	destinationField VARCHAR(75) null,
	fieldType VARCHAR(75) null,
	frequency INTEGER,
	required BOOLEAN
);