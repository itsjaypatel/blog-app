package com.example.demo.assembler;

import com.example.demo.controllers.BlogController;
import com.example.demo.entity.Blog;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BlogModelAssembler implements RepresentationModelAssembler<Blog, EntityModel<Blog>> {
    @Override
    public EntityModel<Blog> toModel(Blog blog) {
        return EntityModel.of(blog,
                linkTo(methodOn(BlogController.class).getById(blog.getId())).withSelfRel(),
                linkTo(methodOn(BlogController.class).getAll()).withRel("blogs"));
    }
}