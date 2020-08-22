CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NULL,
	[Password] [nvarchar](max) NULL,
	[Age] [int] NOT NULL,
	[Sex] [nvarchar](max) NULL

 CONSTRAINT [PK_dbo.Users] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]



INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Weslie', N'123', 12, N'male')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Wolffy', N'456', 34, N'male')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Paddi', N'123', 11, N'male')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Sparky', N'123', 12, N'male')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Tibby', N'123', 11, N'female')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Wilie', N'456', 5, N'male')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'AngelaLily', N'456', 4, N'female')
INSERT [dbo].[Users] ([Name], [Password], [Age], [Sex]) VALUES ( N'Wolnie', N'456', 33, N'female')