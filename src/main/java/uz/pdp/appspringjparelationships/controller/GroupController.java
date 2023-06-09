package uz.pdp.appspringjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjparelationships.entity.Faculty;
import uz.pdp.appspringjparelationships.entity.Group;
import uz.pdp.appspringjparelationships.payload.GroupDto;
import uz.pdp.appspringjparelationships.repository.FacultyRepository;
import uz.pdp.appspringjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    //VAZIRLIK UCHUN
    @GetMapping
    public List<Group> getGroup() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsUniversityId(@PathVariable Integer universityId) {
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        return allByFaculty_universityId;
//        return groupsByUniversityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Such faculty not found";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }
}
