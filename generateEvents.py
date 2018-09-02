import random
import string

adjectives = ["Summer", "Epic", "Legendary", "Original", "Winter", "Fall", "Autum", "Spring", "Best", "Loud", "Smooth", "Cool", "Largest",
              "Biggest", "Fun", "Awesome", "Amusing", "Authentic", "Charming", "Delightful", "Desirable", "Fabulous", "Genius", "Colourful",
              "Glorious", "Innovative", "Lively", "Lovely", "Neat", "Nice", "Miraculous", "Open", "Optimistic", "Peaceful", "Positive"]

events = [ "African blues", "Blues rock", "Blues shouter", "British blues", "Canadian blues", "Chicago blues", "Classic female blues",
           "Contemporary R&B", "Country blues", "Delta blues", "Detroit blues", "Electric blues", "Gospel blues", "Hill country blues",
           "Hokum blues", "Jump blues", "Kansas City blues", "Louisiana blues", "Memphis blues", "Piedmont blues", "Punk blues", "Rhythm and blues",
           "Soul blues", "St. Louis blues", "Swamp blues", "Texas blues", "West Coast blues", "Alternative rock", "Britpop", "Post-Britpop",
           "Dream pop", "Shoegaze", "Grunge", "Post-grunge", "Indie rock", "Dunedin sound", "Math rock", "Post-punk revival", "Sadcore", "Slowcore",
           "Beat music", "Christian rock", "Dark cabaret", "Electronic rock", "Electronicore", "Experimental rock", "Art rock", "Industrial rock",
           "Post-punk", "Gothic rock", "No wave", "Noise rock", "Post-rock", "Post-metal", "Folk rock", "Garage rock", "Glam rock", "Hard rock",
           "Heavy metal", "Alternative metal", "Avant-garde metal", "Black metal", "Symphonic black metal", "Viking metal", "War metal",
           "Christian metal", "Unblack metal", "Death metal", "Melodic death metal", "Technical death metal", "Deathgrind", "National Socialist Black Metal",
           "Doom metal", "Death-doom", "Drone metal", "Folk metal", "Celtic metal", "Medieval metal", "Pagan metal", "Funk metal", "Glam metal",
           "Gothic metal","Grindcore", "Industrial metal", "Kawaii metal", "Latin metal", "Metalcore", "Melodic metalcore", "Deathcore", "Mathcore",
           "Metalstep","Neoclassical metal", "Nu metal", "Post-metal", "Power metal", "Progressive metal", "Djent", "Rap metal", "Sludge metal",
           "Symphonic metal", "Thrash metal", "Crossover thrash", "Groove metal", "Jazz rock", "New wave", "World fusion", "Paisley Underground",
           "Desert rock", "Pop rock", "Soft rock", "Progressive rock", "Canterbury scene", "Krautrock", "New prog", "Rock in Opposition", "Space rock",
           "Psychedelic rock", "Acid rock", "Freakbeat", "Neo-psychedelia", "Raga rock", "Punk rock", "Anarcho punk", "Crust punkRock and roll",
           "Southern rock","Stoner rock", "Sufi rock", "Surf rock", "Visual kei", "Nagoya kei", "Speed metal", "Worldbeat"]


names = ["OLIVIA", "RUBY", "EMILY", "GRACE", "JESSICA", "CHLOE", "SOPHIE", "LILY", "AMELIA", "EVIE", "MIA", "ELLA", "CHARLOTTE", "LUCY",
         "MEGAN", "ELLIE", "ISABELLE", "ISABELLA", "HANNAH", "KATIE", "AVA", "HOLLY", "SUMMER", "MILLIE", "DAISY", "PHOEBE", "FREYA",
         "ABIGAIL", "POPPY", "ERIN", "EMMA", "MOLLY", "IMOGEN", "AMY", "JASMINE", "ISLA", "SCARLETT", "LEAH", "SOPHIA", "ELIZABETH",
         "EVA", "BROOKE", "MATILDA", "CAITLIN", "KEIRA", "ALICE", "LOLA", "LILLY", "AMBER", "ISABEL", "LAUREN", "GEORGIA", "GRACIE",
         "ELEANOR", "BETHANY", "MADISON", "AMELIE", "ISOBEL", "PAIGE", "LACEY", "SIENNA", "LIBBY", "MAISIE", "ANNA", "REBECCA", "ROSIE",
         "TIA", "LAYLA", "MAYA", "NIAMH", "ZARA", "SARAH", "LEXI", "MADDISON", "ALISHA", "SOFIA", "SKYE", "NICOLE", "LEXIE", "FAITH",
         "MARTHA", "HARRIET", "ZOE", "EVE", "JULIA", "AIMEE", "HOLLIE", "LYDIA", "EVELYN", "ALEXANDRA", "MARIA", "FRANCESCA", "TILLY",
         "FLORENCE", "ALICIA", "ABBIE", "EMILIA", "COURTNEY", "MARYAM", "ESME"]

surnames = ["Smith", "Jones", "Taylor", "Williams", "Brown", "Davies", "Evans", "Wilson", "Thomas", "Roberts", "Johnson", "Lewis", "Walker",
            "Robinson", "Wood", "Thompson", "White", "Watson", "Jackson", "Wright", "Green", "Harris", "Cooper", "King", "Lee", "Martin", "Clarke",
            "James", "Morgan", "Hughes", "Edwards", "Hill", "Moore", "Clark", "Harrison", "Scott", "Young", "Morris", "Hall", "Ward", "Turner",
            "Carter", "Phillips", "Mitchell", "Patel", "Adams", "Campbell", "Anderson", "Allen", "Cook", "Bailey", "Parker", "Miller", "Davis",
            "Murphy", "Price", "Bell", "Baker", "Griffiths", "Kelly", "Simpson", "Marshall", "Collins", "Bennett", "Cox", "Richardson", "Fox",
            "Gray", "Rose", "Chapman", "Hunt", "Robertson", "Shaw", "Reynolds", "Lloyd", "Ellis", "Richards", "Russell", "Wilkinson", "Khan",
            "Graham", "Stewart", "Reid", "Murray", "Powell", "Palmer", "Holmes", "Rogers", "Stevens", "Walsh", "Hunter", "Thomson", "Matthews",
            "Ross", "Owen", "Mason", "Knight", "Kennedy", "Butler", "Saunders"]


eventsNumber = 10
population = 50
ticketNumber = 800

generatedNames = []
generatedSurnames = []
generatedLogins = []
generatedPasswords = []

generatedEvents = []
eventTypes = []
generatedTickets = []

#generate events

for i in range(eventsNumber):
    generatedEvents.append(random.choice(adjectives) + " " + random.choice(events) + " " +
                           random.choice(["festival", "concert", "event", "gig"]) + "', '" +
                           random.choice(["small", "medium", "big"]) + "', " +
                           "'2018-" + str(random.randint(1, 12)) + '-' + str(random.randint(1, 28)) + "'")
# print(generatedEvents)
print("INSERT INTO events VALUES")
for i in range(eventsNumber-1):
    print("(" + str(i) + ", '" + generatedEvents[i] + "),")
print("(" + str(eventsNumber-1) + ", '" + generatedEvents[eventsNumber-1] + ");")

#generate tickets

for i in range(ticketNumber):
    generatedTickets.append(str(random.randint(0, eventsNumber-1)) + ", '" + random.choice(["children", "normal", "elderly", "special"]))

print("INSERT INTO tickets VALUES")
for i in range(ticketNumber-1):
    print("(" + str(i) + ", " + generatedTickets[i] + "'),")
print("(" + str(ticketNumber-1) + ", " + generatedTickets[ticketNumber-1] + "');")

#generate users

for i in range(population):
    generatedNames.append(random.choice(names))
    generatedSurnames.append(random.choice(surnames))

print("INSERT INTO USERS(id, name, surname) VALUES")
for i in range(population-1):
    print('(' + str(i) + ", " + "'" + generatedNames[i] + "', '" + generatedSurnames[i] + "'),")
print('(' + str(population-1) + ", " + "'" + generatedNames[population-1] + "', '" + generatedSurnames[population-1] + "');")


for i in range(population):
    generatedLogins.append(generatedNames[i] + generatedSurnames[i] + str(random.randint(10, 50)))
    generatedPasswords.append(''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(10)))

print("INSERT INTO logins VALUES")
for i in range(population-1):
    print("(" + str(i) + ", '" + generatedLogins[i] + "', '" + generatedPasswords[i] + "'),")
print("(" + str(population-1) + ", '" + generatedLogins[population-1] + "', '" + generatedPasswords[population-1] + "');")



#generate purchase history
generatedHistory = []
boughtTickets = []
for _ in range(ticketNumber):
	boughtTickets.append(0)

for i in range(population):
    number = random.randint(0,4) + random.randint(0,4) + random.randint(0,4) + random.randint(0,4) + random.randint(0,4)
    for k in range(number):
        no = random.randint(0, 8000)
        ind = 0
        while boughtTickets[(ind+no)%ticketNumber] == 1:
            ind += 1
        boughtTickets[(ind + no) % ticketNumber] = 1
        generatedHistory.append(str(i) + ", " + str((ind+no)%ticketNumber) + ", '2018-" + str(random.randint(1,8)) + "-" + str(random.randint(1, 27)) + "'")

print("INSERT INTO clients_discounts VALUES")
for i in range(population-1):
	print("(0," + str(i) + "),")
print("(0," + str(population-1) + ");")


print("INSERT INTO purchases VALUES")
for i in range(len(generatedHistory)-1):
    print("(" + generatedHistory[i] + "),")
print("(" + generatedHistory[len(generatedHistory)-1] + ");")


print("INSERT INTO LAST_LOGINS VALUES")
for i in range(population-1):
	for i in range(random.randint(1,10):
		print("("+str(i)+",2018-09-0"+str(random.randint(1,2))+str(random.randint(10,24))+":"+str(random.randint(10,59))+":"+str(random.randint(10,59))+"."+str(random.randint(111111,999999) + "),")
print("("+str(population)+",2018-09-0"+str(random.randint(1,2))+str(random.randint(10,24))+":"+str(random.randint(10,59))+":"+str(random.randint(10,59))+"."+str(random.randint(111111,999999) + ");")

