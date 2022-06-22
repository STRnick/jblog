-- scheme
show tables;
desc user;
desc blog;
desc category;
desc post;

select * from user;
select * from blog;
select * from category;
select * from post;

select c.name as categoryName, c.no, c.blog_id as blogId, c.description, count(p.no) as count, p.title, p.contents, p.category_no 
from category c, post p
where blog_id=3 and c.no = p.category_no
-- group by c.blog_id
order by c.no;

insert into post values(null, '테스트타이틀2', '테스트내용2', 2);

delete from user;
ALTER TABLE user AUTO_INCREMENT=1;

insert into post
values(null, '테스트 타이틀1', '테스트 내용', 2)
select p.no, p.title, p.contents, p.category_no
from post p, category c
where c.no = 2 and c.no = p.category_no;
