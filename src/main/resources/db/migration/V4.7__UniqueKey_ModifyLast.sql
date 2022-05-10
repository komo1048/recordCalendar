ALTER TABLE `calendar`
	DROP INDEX `user`,
	DROP INDEX `start`,
	ADD UNIQUE INDEX `start` (`start`, `user`) USING BTREE;