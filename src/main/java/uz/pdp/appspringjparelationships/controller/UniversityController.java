package uz.pdp.appspringjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjparelationships.entity.Address;
import uz.pdp.appspringjparelationships.entity.University;
import uz.pdp.appspringjparelationships.payload.UniversityDto;
import uz.pdp.appspringjparelationships.repository.AddressRepository;
import uz.pdp.appspringjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    //READ
    @RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversities(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //POST
    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){

        //yangi adres ochib oldik

        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());

        //yasab olgan adres objectimizni DB ga saqladik va u bizga saqlangan adresni berdi
        Address saveAddress = addressRepository.save(address);

        //yangi universitet ochdik
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(saveAddress);
        universityRepository.save(university);

        return "university added";
    }

    //UPDATE
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){

            University university = optionalUniversity.get();
            university.setName(universityDto.getName());

            //universitet adresi
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }

    //DELETE
    @RequestMapping(value = "university/{id}",method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University deleted";
    }
}
