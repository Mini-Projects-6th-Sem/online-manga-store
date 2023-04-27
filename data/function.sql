DELIMITER &&  
CREATE PROCEDURE get_total ()  
BEGIN  
    SELECT * FROM manga WHERE price < 200;  
    SELECT COUNT(TITLE)  FROM student_info;    
END &&  
DELIMITER ;