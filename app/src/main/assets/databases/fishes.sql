BEGIN TRANSACTION;
CREATE TABLE "fishDB" (
	`_id`	TEXT,
	`scientificName`	TEXT,
	`images`	TEXT,
	`recommendation`	TEXT,
	`inSeason`	INTEGER,
	`primaryImage`	TEXT
);
INSERT INTO `fishDB` VALUES ('Prink brotula','Brotula clarkae','brotulaclarkae1.jpg','Acceptable',1,'brotulaclarkae1.jpg');
INSERT INTO `fishDB` VALUES ('Pacific crevalle jack','Caranx caninus
','caranxcaninus1.jpg','Recommended',1,'caranxcaninus1.jpg');
CREATE TABLE "android_metadata" (
	`locale`	TEXT DEFAULT 'en_US'
);
COMMIT;
