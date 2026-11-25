using Microsoft.AspNetCore.Authentication.JwtBearer;

var builder = WebApplication.CreateBuilder(args);

builder.Services
    .AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.Authority =
            "http://localhost:8080/realms/recruitment-platform";

        options.Audience = "identity-api";

        options.RequireHttpsMetadata = false;
        options.MapInboundClaims = false;

        options.Events = new JwtBearerEvents
        {
            OnAuthenticationFailed = context =>
            {
                Console.WriteLine("AUTH FAILED:");
                Console.WriteLine(context.Exception.Message);
                return Task.CompletedTask;
            },

            OnTokenValidated = context =>
            {
                Console.WriteLine("TOKEN VALIDATED");
                return Task.CompletedTask;
            }
        };
    });

builder.Services.AddAuthorization();

builder.Services.AddOpenApi();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.MapGet("/api/secure", () =>
    {
        return Results.Ok(new
        {
            message = "You are authenticated"
        });
    })
    .RequireAuthorization();

app.MapGet("/api/me", (HttpContext context) =>
{
    var user = context.User;

    return Results.Ok(new
    {
        subject = user.FindFirst("sub")?.Value,
        username = user.FindFirst("preferred_username")?.Value,
        email = user.FindFirst("email")?.Value
    });
}).RequireAuthorization();

app.Run();