use starshop;

INSERT INTO categories (name, is_published) VALUES 
('Hoa Cưới', true),
('Hoa Chúc Mừng', true),
('Hoa Chia Buồn', true),
('Hoa Tình Yêu', true),
('Hoa Tết', true);

INSERT INTO products (name, description, weight, cost_price, sale_price, discount_percent, current_quantity, image, is_published) VALUES
('Cẩm Tú Cầu', 'Bó hoa cẩm tú cầu tinh tế dành cho lễ cưới', 0.2, 150000, 200000, 10, 50, 'hydrangea.jpg', true),
('Hồng Trắng', 'Bó hoa hồng trắng tinh khiết', 0.15, 120000, 180000, 5, 60, 'rose_white.jpg', true),
('Lily', 'Hoa lily trắng thanh lịch', 0.25, 180000, 250000, 0, 45, 'lily_white.jpg', true),
('Cát Tường', 'Bó hoa cát tường sang trọng', 0.18, 140000, 210000, 8, 40, 'lisianthus.jpg', true),
-- 4
('Hồng Đỏ', 'Bó hoa hồng đỏ rực rỡ', 0.1, 60000, 90000, 10, 80, 'rose_red.jpg', true),
('Cẩm Chướng', 'Hoa cẩm chướng đa sắc màu', 0.15, 70000, 110000, 0, 75, 'carnation.jpg', true),
('Hướng Dương', 'Hoa hướng dương rực rỡ', 0.3, 80000, 130000, 5, 90, 'sunflower.jpg', true),
('Cúc Họa Mi', 'Hoa cúc họa mi dễ thương', 0.12, 50000, 80000, 0, 100, 'daisy.jpg', true),
-- 8
('Lan Hồ Điệp', 'Chậu lan hồ điệp sang trọng', 0.5, 250000, 400000, 10, 30, 'orchid.jpg', true),
('Hồng Cam', 'Bó hoa hồng cam tươi tắn', 0.15, 90000, 130000, 5, 60, 'rose_orange.jpg', true),
('Cẩm Tú Cầu Xanh', 'Bó hoa cẩm tú cầu xanh nhẹ nhàng', 0.2, 150000, 220000, 8, 45, 'hydrangea_green.jpg', true),
('Baby Trắng', 'Hoa baby trắng thanh lịch', 0.05, 40000, 70000, 0, 120, 'baby_breath.jpg', true),
-- 12
('Mai Vàng', 'Chậu mai vàng rực rỡ đón Tết', 0.4, 500000, 750000, 5, 25, 'apricot_blossom.jpg', true),
('Đào Hồng', 'Cành đào hồng đẹp cho Tết', 0.35, 450000, 700000, 0, 30, 'peach_blossom.jpg', true),
('Cúc Mâm Xôi', 'Hoa cúc mâm xôi đón xuân', 0.25, 200000, 300000, 5, 40, 'mum.jpg', true),
('Cát Tường Xuân', 'Hoa cát tường đón xuân', 0.15, 150000, 220000, 10, 35, 'lisianthus_tet.jpg', true),
-- 16
('Hồng Tình Yêu', 'Bó hoa hồng đỏ dành cho Valentine', 0.1, 70000, 100000, 0, 90, 'rose_valentine.jpg', true),
('Cẩm Chướng Hồng', 'Hoa cẩm chướng hồng ngọt ngào', 0.12, 60000, 90000, 5, 80, 'carnation_pink.jpg', true),
('Baby Nhẹ Nhàng', 'Hoa baby trắng dịu dàng', 0.05, 50000, 80000, 0, 100, 'baby_breath_pink.jpg', true),
('Ly Hồng', 'Hoa ly hồng ngọt ngào', 0.2, 180000, 250000, 10, 50, 'lily_pink.jpg', true),
-- 20
('Hoa Tulip', 'Bó hoa tulip đa dạng màu', 0.1, 60000, 90000, 0, 70, 'rose_white_occasion.jpg', true),
('Cẩm Tú Cầu Đỏ', 'Hoa cẩm tú cầu đa sắc cho lễ hội', 0.2, 120000, 180000, 5, 65, 'hydrangea_ocassion.jpg', true),
('Bó hoa Hướng Dương', 'Bó hoa hướng dương nổi bật', 0.3, 100000, 150000, 0, 85, 'sunflower_occasion.jpg', true),
('Lan Tím', 'Hoa lan hồ điệp tím cho dịp lễ', 0.4, 250000, 400000, 8, 40, 'orchid_purple.jpg', true),
-- 24
('Cúc Trắng', 'Hoa cúc trắng tinh khôi', 0.1, 50000, 75000, 5, 90, 'daisy_march.jpg', true),
('Vòng hoa chia buồn', 'Chia buồn bằng vòng hoa màu trắng', 0.1, 60000, 90000, 0, 95, 'sad1.jpg', true),
('Oải Hương', 'Hoa oải hương thơm mát', 0.05, 65000, 95000, 10, 70, 'lavender.jpg', true),
('Mẫu Đơn', 'Hoa mẫu đơn lớn, tinh tế', 0.25, 120000, 180000, 0, 80, 'peony.jpg', true),
-- 28
('Ly Hồng Ngọt Ngào', 'Hoa ly hồng tặng phái đẹp', 0.2, 160000, 230000, 8, 60, 'lily_pink_womensday.jpg', true),
('Hồng Tươi', 'Bó hoa hồng đỏ tươi mừng ngày 8/3', 0.1, 70000, 100000, 0, 75, 'rose_red_womensday.jpg', true),
('Hoa Baby', 'Hoa baby trắng nhẹ nhàng', 0.05, 55000, 85000, 5, 85, 'baby_breath_womensday.jpg', true),
('Lan Hồ Điệp Trắng', 'Hoa lan hồ điệp trắng tinh tế', 0.3, 200000, 350000, 10, 55, 'orchid_white_womensday.jpg', true);
-- 32

INSERT INTO product_categories (product_id, category_id) VALUES

-- 1 ('Hoa Cưới', true),
(1, 1), (2, 1), (3, 1), (4, 1),
(5, 1), (6, 1), (7, 1), (8, 1),
(9, 1), (10, 1), (11, 1), (12, 1),
(29, 1), (30, 1), (31, 1), (32, 1),

-- 2 ('Hoa Chúc Mừng', true),
(5, 2), (6, 2), (7, 2), (8, 2),
(9, 2), (10, 2), (11, 2), (12, 2),
(22, 2), (23, 2), (24, 2), (32, 2),

-- 3 ('Hoa Chia Buồn', true),
(25, 4), (26, 4), (27, 4), (28, 4),

-- 4 ('Hoa Tình Yêu', true),
(1, 4), (2, 4), (3, 4),
(5, 4), (6, 4), (7, 4), (8, 4),
(17, 4), (18, 4), (19, 4), (20, 4), (21,4),

-- 5 ('Hoa Tết', true),
(13, 5), (14, 5), (15, 5), (16, 5);