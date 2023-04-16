package uz.pdp.appspringjparelationships.payload;

import lombok.Data;

@Data
public class UniversityDto {

    //malumotlarni tashish uchun xizmat qiladi

    private String name;
    private String city;
    private String district;
    private String street;
}
