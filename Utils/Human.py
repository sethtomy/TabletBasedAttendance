class Person():

    def __init__(self, email, first_name, last_name):
        self.email = email
        self.first_name = first_name
        self.last_name = last_name

    def to_dict(self):
        dest = {
            'email': self.email,
            'firstName': self.first_name,
            'lastName': self.last_name
        }
        return dest


class Student(Person):

    def __init__(self, email, first_name, last_name):
        super().__init__(email, first_name, last_name)


class Instructor(Person):

    def __init__(self, email, first_name, last_name, auth_token='Invalid Test Token'):
        super().__init__(email, first_name, last_name)
        self.auth_token = auth_token
        self.course_list = []

    def to_dict(self):
        dest = super().to_dict()
        dest['auth_token'] = self.auth_token
        dest['course_list'] = self.course_list
        return dest