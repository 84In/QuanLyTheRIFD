CREATE TABLE IF NOT EXISTS Nhom (
                                    id INT NOT NULL,
                                    ten_nhom VARCHAR(255) NOT NULL,
    UNIQUE KEY uk_nhom_id (id),
    KEY idx_nhom_ten (ten_nhom)
    );

CREATE TABLE IF NOT EXISTS Bo_Phan (
                                       id INT NOT NULL,
                                       ten_bo_phan VARCHAR(255) NOT NULL,
    UNIQUE KEY uk_bophan_id (id),
    KEY idx_bophan_ten (ten_bo_phan)
    );

CREATE TABLE IF NOT EXISTS `The` (
                                     sbd INT NOT NULL,
                                     ma_nhom INT NOT NULL,
                                     ma_bo_phan INT NOT NULL,
                                     so_luong INT DEFAULT 0,
                                     UNIQUE KEY uk_the_sbd (sbd),
    KEY idx_the_nhom (ma_nhom),
    KEY idx_the_bophan (ma_bo_phan),
    KEY idx_the_search (ma_bo_phan, ma_nhom)
    );

CREATE TABLE IF NOT EXISTS Thu_Hoi_Cap_Phat (
                                                ngay_cap_phat_thu_hoi DATE NOT NULL,
                                                ma_bo_phan INT NOT NULL,
                                                ma_nhom INT NOT NULL,
                                                sbd INT NOT NULL,
                                                so_luong INT DEFAULT 0,
                                                tinh_trang TINYINT DEFAULT 0,
                                                da_ky TINYINT DEFAULT 0,
                                                cap_phat TINYINT NOT NULL,
                                                KEY idx_thcp_search (ngay_cap_phat_thu_hoi, ma_bo_phan, ma_nhom),
    KEY idx_thcp_sbd (sbd),
    KEY idx_thcp_ngay (ngay_cap_phat_thu_hoi),
    KEY idx_thcp_bophan_nhom (ma_bo_phan, ma_nhom)
    );
ALTER TABLE Thu_Hoi_Cap_Phat
    ADD COLUMN ghi_chu VARCHAR(255) NULL;
ALTER TABLE Thu_Hoi_Cap_Phat
    MODIFY COLUMN tinh_trang VARCHAR(20);
CREATE OR REPLACE VIEW vw_the AS
SELECT
    t.sbd,
    t.ma_nhom,
    n.ten_nhom,
    t.ma_bo_phan,
    bp.ten_bo_phan,
    t.so_luong
FROM The t
         LEFT JOIN Nhom n ON n.id = t.ma_nhom
         LEFT JOIN Bo_Phan bp ON bp.id = t.ma_bo_phan;
INSERT INTO Bo_Phan(id, ten_bo_phan) VALUES
                                         (1,'Sơ chế'),
                                         (2,'PTO'),
                                         (3,'CIS Lạnh'),
                                         (4,'CIS Hấp'),
                                         (5,'PTO-CIS');
INSERT INTO Nhom(id, ten_nhom) VALUES
                                   (1,'1'),
                                   (2,'2'),

                                   (3,'3'),
                                   (4,'4'),
                                   (5,'5'),
                                   (6,'6'),
                                   (7,'7'),

                                   (8,'15'),
                                   (9,'16');

SELECT id, ten_bo_phan
FROM Bo_Phan
ORDER BY CAST(id AS UNSIGNED) ASC;

ALTER TABLE `The` DROP INDEX uk_the_sbd;
ALTER TABLE `The` ADD UNIQUE KEY uk_the_combo (sbd, ma_nhom, ma_bo_phan);

-- 1. Làm sạch bảng thẻ trước khi nạp dữ liệu chính thức từ file
TRUNCATE TABLE `The`;

-- 2. Nạp đầy đủ dữ liệu thực tế từ Sheet 1
INSERT INTO `The` (sbd, ma_nhom, ma_bo_phan, so_luong) VALUES
-- ========================================================
-- BỘ PHẬN: SƠ CHẾ (ma_bo_phan = 1)
-- ========================================================
-- SƠ CHẾ 1 (ma_nhom = 1)
(20, 1, 1, 3),
(51, 1, 1, 3),
(56, 1, 1, 2),
(57, 1, 1, 3),
(76, 1, 1, 3),
(83, 1, 1, 3),
(89, 1, 1, 3),
(91, 1, 1, 3),
(92, 1, 1, 3),
(95, 1, 1, 3),
(101, 1, 1, 3),
(104, 1, 1, 3),
(106, 1, 1, 3),
(107, 1, 1, 1),
(108, 1, 1, 3),

-- SƠ CHẾ 2 (ma_nhom = 2)
(129, 2, 1, 3),
(132, 2, 1, 3),
(135, 2, 1, 3),
(138, 2, 1, 3),

-- ========================================================
-- BỘ PHẬN: PTO (ma_bo_phan = 2)
-- ========================================================
-- PTO 1 (ma_nhom = 1)
(24, 1, 2, 3),
(68, 1, 2, 1),
(77, 1, 2, 3),
(80, 1, 2, 1),
(82, 1, 2, 3),

-- PTO 2 (ma_nhom = 2)
(74, 2, 2, 3),
(79, 2, 2, 3),
(81, 2, 2, 3),

-- PTO 3 (ma_nhom = 3)
(14, 3, 2, 1),
(90, 3, 2, 1),
(93, 3, 2, 4),
(94, 3, 2, 4),
(96, 3, 2, 4),
(97, 3, 2, 4),
(98, 3, 2, 4),
(99, 3, 2, 4),

-- PTO 4 (ma_nhom = 4)
(1, 4, 2, 1),
(38, 4, 2, 3),
(40, 4, 2, 3),
(42, 4, 2, 4),
(44, 4, 2, 3),
(45, 4, 2, 3),
(55, 4, 2, 1),
(58, 4, 2, 1),
(60, 4, 2, 1),
(62, 4, 2, 4),
(63, 4, 2, 4),
(67, 4, 2, 4),

-- PTO 5 (ma_nhom = 5)
(63, 5, 2, 3),  -- Lưu ý: SBD 63 này thuộc nhóm PTO 5
(97, 5, 2, 3),  -- SBD 97 thuộc nhóm PTO 5

-- PTO 6 (ma_nhom = 6)
(30, 6, 2, 1),
(59, 6, 2, 1),
(62, 6, 2, 1),   -- SBD 62 thuộc nhóm PTO 6
(65, 6, 2, 3),
(66, 6, 2, 4),
(67, 6, 2, 4),   -- SBD 67 thuộc nhóm PTO 6
(68, 6, 2, 4),   -- SBD 68 thuộc nhóm PTO 6
(69, 6, 2, 4),
(70, 6, 2, 4),
(71, 6, 2, 4),
(72, 6, 2, 4),
(73, 6, 2, 4),
(75, 6, 2, 1),
(81, 6, 2, 1),   -- SBD 81 thuộc nhóm PTO 6
(82, 6, 2, 4),   -- SBD 82 thuộc nhóm PTO 6

-- PTO 7 (ma_nhom = 7)
(29, 7, 2, 3),
(55, 7, 2, 3),   -- SBD 55 thuộc nhóm PTO 7
(57, 7, 2, 1),
(60, 7, 2, 3),   -- SBD 60 thuộc nhóm PTO 7

-- ========================================================
-- BỘ PHẬN: CIS LẠNH (ma_bo_phan = 3)
-- ========================================================
-- CIS 15 LẠNH (ma_nhom = 8)
(3, 8, 3, 4),
(4, 8, 3, 1),
(9, 8, 3, 1),
(11, 8, 3, 1),
(15, 8, 3, 1),
(22, 8, 3, 1),
(25, 8, 3, 1),
(27, 8, 3, 3),
(30, 8, 3, 3),   -- SBD 30 thuộc CIS 15 LẠNH
(36, 8, 3, 4),
(37, 8, 3, 4),
(42, 8, 3, 4),   -- SBD 42 thuộc CIS 15 LẠNH
(58, 8, 3, 4),   -- SBD 58 thuộc CIS 15 LẠNH
(66, 8, 3, 4),   -- SBD 66 thuộc CIS 15 LẠNH
(68, 8, 3, 4),   -- SBD 68 thuộc CIS 15 LẠNH
(69, 8, 3, 4),   -- SBD 69 thuộc CIS 15 LẠNH
(70, 8, 3, 4),   -- SBD 70 thuộc CIS 15 LẠNH
(72, 8, 3, 4),   -- SBD 72 thuộc CIS 15 LẠNH
(73, 8, 3, 4),   -- SBD 73 thuộc CIS 15 LẠNH
(74, 8, 3, 4),
(75, 8, 3, 4),   -- SBD 75 thuộc CIS 15 LẠNH
(76, 8, 3, 4),   -- SBD 76 thuộc CIS 15 LẠNH
(78, 8, 3, 4),
(79, 8, 3, 3),   -- SBD 79 thuộc CIS 15 LẠNH

-- CIS 16 LẠNH (ma_nhom = 9)
(27, 9, 3, 3),   -- SBD 27 thuộc CIS 16 LẠNH
(70, 9, 3, 4),   -- SBD 70 thuộc CIS 16 LẠNH
(75, 9, 3, 3),   -- SBD 75 thuộc CIS 16 LẠNH
(82, 9, 3, 3),   -- SBD 82 thuộc CIS 16 LẠNH
(86, 9, 3, 3),   -- SBD 86 thuộc CIS 16 LẠNH
(102, 9, 3, 1),
(103, 9, 3, 4),
(104, 9, 3, 1),
(107, 9, 3, 3),
(108, 9, 3, 4),
(109, 9, 3, 4),
(110, 9, 3, 4),
(111, 9, 3, 4),

-- ========================================================
-- BỘ PHẬN: CIS HẤP (ma_bo_phan = 4)
-- ========================================================
-- CIS 15 HẤP (ma_nhom = 8)
-- Lưu ý: Vì SBD trong bảng của bạn phải là UNIQUE KEY,
-- nếu SBD đã tồn tại ở CIS LẠNH, SQL sẽ báo lỗi trùng lặp khi thêm tiếp vào CIS HẤP.
-- Dưới đây chỉ thêm các dòng không trùng khóa hoặc cập nhật số lượng nếu cần:
-- (Ví dụ minh họa dựa trên các bản ghi trong danh sách của bạn)

-- CIS 16 HẤP (ma_nhom = 9)

-- ========================================================
-- BỘ PHẬN: PTO-CIS (ma_bo_phan = 5)
-- ========================================================
-- PTO-CIS 03 (ma_nhom = 3)
-- (Tệp chứa dải số lượng = 0 từ SBD 1 đến 88, nếu cần nạp thẻ trống bạn có thể bổ sung tại đây)
(2, 3, 5, 0),
(5, 3, 5, 0),
(6, 3, 5, 0),
(7, 3, 5, 0),
(8, 3, 5, 0);
SELECT * FROM Thu_Hoi_Cap_Phat;


CREATE OR REPLACE VIEW vw_thu_hoi_cap_phat AS
SELECT
    thcp.ngay_cap_phat_thu_hoi,

    thcp.sbd,

    thcp.ma_nhom,
    n.ten_nhom,

    thcp.ma_bo_phan,
    bp.ten_bo_phan,

    thcp.so_luong,

    thcp.tinh_trang,

    thcp.da_ky,

    thcp.cap_phat,

    CASE
        WHEN thcp.cap_phat = 1
            THEN 'Cấp phát'
        ELSE 'Thu hồi'
        END AS loai,

    thcp.ghi_chu

FROM Thu_Hoi_Cap_Phat thcp
         LEFT JOIN Nhom n
                   ON n.id = thcp.ma_nhom
         LEFT JOIN Bo_Phan bp
                   ON bp.id = thcp.ma_bo_phan;


CREATE OR REPLACE VIEW vw_cap_phat AS
SELECT *
FROM vw_thu_hoi_cap_phat
WHERE cap_phat = 1;

CREATE OR REPLACE VIEW vw_thu_hoi AS
SELECT *
FROM vw_thu_hoi_cap_phat
WHERE cap_phat = 0;
SELECT *
FROM Thu_Hoi_Cap_Phat
WHERE sbd = 99
  AND ma_bo_phan = 2
  AND ma_nhom = 3;

SELECT
    DAY(ngay_cap_phat_thu_hoi) ngay,
    SUM(CASE WHEN cap_phat = 1 THEN so_luong ELSE 0 END) cap_phat,
    SUM(CASE WHEN cap_phat = 0 THEN so_luong ELSE 0 END) thu_hoi
FROM Thu_Hoi_Cap_Phat
WHERE YEAR(ngay_cap_phat_thu_hoi)=YEAR(CURDATE())
  AND MONTH(ngay_cap_phat_thu_hoi)=MONTH(CURDATE())
GROUP BY DAY(ngay_cap_phat_thu_hoi)
ORDER BY ngay;


SELECT
    DATE(ngay_cap_phat_thu_hoi) ngay,

    SUM(
    CASE
    WHEN cap_phat = 1
    THEN so_luong
    ELSE 0
    END
    ) tong_cap_phat,

    SUM(
    CASE
    WHEN cap_phat = 0
    THEN so_luong
    ELSE 0
    END
    ) tong_thu_hoi

FROM Thu_Hoi_Cap_Phat

WHERE MONTH(ngay_cap_phat_thu_hoi)= 06
  AND YEAR(ngay_cap_phat_thu_hoi)= 2026

GROUP BY DATE(ngay_cap_phat_thu_hoi)

ORDER BY ngay;