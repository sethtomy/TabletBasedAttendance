from Person import Person

class Instructor(Person):

    def __init__(self, email, first_name, last_name, auth_token):
        super().__init__(email, first_name, last_name)
        self.auth_token = auth_token
        course_list = [1, 2, 3]

