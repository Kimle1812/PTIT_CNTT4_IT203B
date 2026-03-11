package Exercise06;


public class Exercide06 {
    private ProfileService service;
    private List<User> allUsers;
    private User existingUser;

    @BeforeEach
    void setUp() {
        service = new ProfileService();
        allUsers = new ArrayList<>();
        existingUser = new User("me@test.com", new UserProfile("me@test.com", LocalDate.of(1990, 1, 1)));
        allUsers.add(existingUser);
    }

    @Test
    void testUpdate_Success() {
        UserProfile newProfile = new UserProfile("new@test.com", LocalDate.of(1995, 1, 1));
        User result = service.updateProfile(existingUser, newProfile, allUsers);
        assertNotNull(result);
    }

    @Test
    void testUpdate_FutureBirthDate_Fails() {
        UserProfile newProfile = new UserProfile("me@test.com", LocalDate.now().plusDays(1));
        User result = service.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }

    @Test
    void testUpdate_DuplicateEmail_Fails() {
        allUsers.add(new User("other@test.com", new UserProfile("other@test.com", LocalDate.of(1990, 1, 1))));
        UserProfile newProfile = new UserProfile("other@test.com", LocalDate.of(1990, 1, 1));
        User result = service.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }

    @Test
    void testUpdate_SameEmail_Success() {
        UserProfile newProfile = new UserProfile("me@test.com", LocalDate.of(1990, 1, 1));
        User result = service.updateProfile(existingUser, newProfile, allUsers);
        assertNotNull(result);
    }

    @Test
    void testUpdate_EmptyUserList_Success() {
        List<User> emptyList = new ArrayList<>();
        UserProfile newProfile = new UserProfile("new@test.com", LocalDate.of(1990, 1, 1));
        User result = service.updateProfile(existingUser, newProfile, emptyList);
        assertNotNull(result);
    }

    @Test
    void testUpdate_MultipleViolations_Fails() {
        allUsers.add(new User("other@test.com", new UserProfile("other@test.com", LocalDate.of(1990, 1, 1))));
        UserProfile newProfile = new UserProfile("other@test.com", LocalDate.now().plusDays(1));
        User result = service.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }
}