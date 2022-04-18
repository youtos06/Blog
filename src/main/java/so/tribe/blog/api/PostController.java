package so.tribe.blog.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import so.tribe.blog.configuration.ApiPath;
import so.tribe.blog.exception.InvalidParameterException;
import so.tribe.blog.service.PostService;

@RestController
@RequestMapping(ApiPath.LEVEL+ApiPath.POSTS)
public class PostController {

    @Autowired
    private PostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @GetMapping("")
    public ResponseEntity<?> getPosts(
            @RequestParam(value = "tags") String tags,
            @RequestParam(value = "sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(value = "direction",required = false,defaultValue = "asc") String direction ) throws InvalidParameterException {

        log.info("Calling "+ApiPath.LEVEL+ApiPath.POSTS);
        if(!sortBy.equals("id") && !sortBy.equals("likes") && !sortBy.equals("reads") && !sortBy.equals("popularity") ){
            throw new InvalidParameterException("sortBy","String");
        }else if(!direction.equals("asc") && !direction.equals("desc")){
            throw new InvalidParameterException("direction","String");
        }
        else{
            return new ResponseEntity<>(postService.consumePostListFromApi(tags,sortBy,direction), HttpStatus.OK);
        }

    }
}
