INSERT into customer VALUES
    ("Nikith", "1PN31", "#52, New Mexico", "abcde@gmail.com", "7829843572"),
    ("Swara", "1PN32", "#21, Hittymon", "what@gmail.com", "4206887884"),
    ("The Wok", "1PN33", "#41, Peshawar", "bingchilling@gmail.com", "4545454545"),
    ('John Xina', '1PN34', '#54, Chyongmin', 'allhailxijinping@gmail.com', '1000000000'),
    ("Big chungus", "1PN35", "Suii Layout", "chunga@ayo.com","7896541230");

INSERT INTO mangaka VALUES 
    ("Kentaro Miura", "miruaberserk@gmail.com"),
    ("Akira Toriyama", "akiratoriyama@gmail.com"),
    ("Gege Atakumi", "atakumisensei@gmail.com"),
    ("Tite Kubo", "kuboamanga@gmail.com"),
    ("One", "onemanga@gmail.com");

INSERT INTO publisher VALUES 
    ("Shueisha", "Kyoto, Japan", "shueisha@gmail.com", "4567891230"),
    ("VIZ media", "Okinawa, Japan", "viz@gmail.com", "7894561230"),
    ("Hakusensha", "Roppongi, Japan", "hakusensha@gmail.com", "9865321470"),
    ("Crunchyroll", "Shibuya, Japan", "crunchyrollasia@gmail.com", "8794651320");

INSERT INTO warehouse VALUES 
    ("25JK256", 180, "A1"),
    ("26BZ246", 250, "A1"),
    ("27DB276", 300, "A2"),
    ("24BL246", 100, "B1"),
    ("22OP226", 50, "B1");

INSERT INTO manga VALUES 
    ("95321678450123", "Kentaro Miura", "Berserk", 250.00, "#21", 3, "Hakusensha", "25JK256"),
    ("95878878450123", "Akira Toriyama", "Dragon Ball Super", 200.00, "#78", 14, "VIZ media", "27DB276"),
    ("78787545450123", "Gege Atakumi", "Jujutsu Kaisen", 200.00, "#51", 6, "Shueisha", "25JK256"),
    ("95877845450123", "Tite Kubo", "Bleach", 250.00, "#150", 13, "Shueisha", "24BL246"),
    ("95321544845454", "One", "One Punch Man", 250.00, "#171", 16, "Shueisha", "22OP226");

INSERT INTO shopping_basket VALUES
    ("111", "1PN31"),
    ("122", "1PN32"),
    ("123", "1PN33"),
    ("131", "1PN34"),
    ("132", "1PN35");

INSERT INTO container VALUES 
    (2, "95321678450123", "111"),
    (4, "95878878450123", "122"),
    (1, "78787545450123", "123"),
    (3, "95877845450123", "131"),
    (4, "95321544845454", "132");