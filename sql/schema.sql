CREATE TABLE `seoul_trft_sttn_pssngr` (
	`LINE_NM` VARCHAR(10) NOT NULL COMMENT '호선' COLLATE 'utf8mb4_unicode_ci',
	`STATION_NUM` VARCHAR(5) NOT NULL COMMENT '역번호' COLLATE 'utf8mb4_unicode_ci',
	`STATION_NM` VARCHAR(50) NOT NULL COMMENT '역명' COLLATE 'utf8mb4_unicode_ci',
	`JAN_CNT` INT NOT NULL COMMENT '1월승하차인원',
	`FAB_CNT` INT NOT NULL COMMENT '2월승하차인원',
	`MAR_CNT` INT NOT NULL COMMENT '3월승하차인원',
	`APR_CNT` INT NOT NULL COMMENT '4월승하차인원',
	`MAY_CNT` INT NOT NULL COMMENT '5월승하차인원',
	`JUN_CNT` INT NOT NULL COMMENT '6월승하차인원',
	`JUL_CNT` INT NOT NULL COMMENT '7월승하차인원',
	`AUG_CNT` INT NOT NULL COMMENT '8월승하차인원',
	`SEP_CNT` INT NOT NULL COMMENT '9월승하차인원',
	`OCT_CNT` INT NOT NULL COMMENT '10월승하차인원',
	`NOV_CNT` INT NOT NULL COMMENT '11월승하차인원',
	`DEC_CNT` INT NOT NULL COMMENT '12월승하차인원'
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;