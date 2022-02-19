package myApi;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	  private final UserRepository repository;

	  private final UserModelAssembler assembler;

	  UserController(UserRepository repository, UserModelAssembler assembler) {

	    this.repository = repository;
	    this.assembler = assembler;
	  }


	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/user")
	  CollectionModel<EntityModel<User>> all() {

	    List<EntityModel<User>> users = repository.findAll().stream() //
	        .map(assembler::toModel) //
	        .collect(Collectors.toList());

	    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	  }
	  // end::get-aggregate-root[]

	  @PostMapping("/user")
	  	ResponseEntity<?> newUser(@RequestBody User newUser) {

		  EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

		  return ResponseEntity //
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
		}
	  // Single item
	  
	  @GetMapping("/user/{id}")
	  EntityModel<User> one(@PathVariable Long id) {

	    User user = repository.findById(id) //
	        .orElseThrow(() -> new UserNotFoundException(id));

	    return assembler.toModel(user);
	  }
	  
	  @PatchMapping("/user/{id}")
	  ResponseEntity<?> replaceCredit(@RequestBody User newUser, @PathVariable Long id) {

	    User updatedUser = repository.findById(id) //
	        .map(user -> {
	          user.setCredit(newUser.getCredit());
	          return repository.save(user);
	        }) //
	        .orElseGet(() -> {
	          newUser.setId(id);
	          return repository.save(newUser);
	        });

	    EntityModel<User> entityModel = assembler.toModel(updatedUser);

	    return ResponseEntity //
	        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	        .body(entityModel);
	  }
	  
	  @PutMapping("/user/{id}")
	  ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {

	    User updatedUser = repository.findById(id) //
	        .map(user -> {
	          user.setName(newUser.getName());
	          user.setEmail(newUser.getEmail());
	          user.setPhone(newUser.getPhone());
	          user.setCredit(newUser.getCredit());
	          return repository.save(user);
	        }) //
	        .orElseGet(() -> {
	          newUser.setId(id);
	          return repository.save(newUser);
	        });

	    EntityModel<User> entityModel = assembler.toModel(updatedUser);

	    return ResponseEntity //
	        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	        .body(entityModel);
	  }
	  @DeleteMapping("/user/{id}")
	  ResponseEntity<?> deleteUser(@PathVariable Long id) {

	    repository.deleteById(id);

	    return ResponseEntity.noContent().build();
	  }

}
